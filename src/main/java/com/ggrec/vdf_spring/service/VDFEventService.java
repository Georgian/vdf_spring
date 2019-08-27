package com.ggrec.vdf_spring.service;

import com.ggrec.vdf_spring.domain.VDFEvent;
import com.ggrec.vdf_spring.domain.VDFEventTag;
import com.ggrec.vdf_spring.repository.VDFEventRepository;
import com.ggrec.vdf_spring.repository.VDFEventTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VDFEventService {

    private static final String EVENT_FOLDER = "event";
    private static final String COVER_PHOTO_FILENAME = "cover.jpg";

    private VDFEventRepository vdfEventRepository;
    private VDFEventTagRepository vdfEventTagRepository;
    private AWSClient awsClient;

    @Autowired
    public VDFEventService(
            VDFEventRepository vdfEventRepository,
            VDFEventTagRepository vdfEventTagRepository,
            AWSClient awsClient) {
        this.vdfEventRepository = vdfEventRepository;
        this.vdfEventTagRepository = vdfEventTagRepository;
        this.awsClient = awsClient;
    }

    public void save(VDFEvent vdfEvent) throws IOException {
        VDFEvent savedEvent = vdfEventRepository.save(vdfEvent);

        if (vdfEvent.doesPhotoLinkNeedUpdating()) {
            String coverPhotoName = MessageFormat.format("{0}/{1}/{2}", EVENT_FOLDER, savedEvent.getId(), COVER_PHOTO_FILENAME);
            URL coverPhotoLink = new URL(vdfEvent.getPhotoLink_Original());
            awsClient.uploadFileFromURL(coverPhotoLink, coverPhotoName, "image/jpeg");
        }
    }

    public void delete(long id) {
        vdfEventRepository.deleteById(id);
    }

    public VDFEvent getById(long id) {
        return vdfEventRepository.getOne(id);
    }

    public List<VDFEvent> getAll() {
        return vdfEventRepository.findAll();
    }

    public List<VDFEventTag> getAllTags() {
        return vdfEventTagRepository.findAll();
    }

    public List<VDFEvent> getFutureEvents() {
        final LocalDate yesterday = LocalDate.now().minusDays(1);
        return getAll().stream()
                .filter(event -> event.getDateStart().isAfter(yesterday))
                .collect(Collectors.toList());
    }

}
