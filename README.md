# New machine setup

1. Install h2 for accessing the DB. You can specify the DB file in application-dev.yml
2. Grab profile application-prod.yml from the secret place, to connect to the prod DB.
3. Set AWS credentials in '.aws/credentials'

# New AWS account setup

1. Create Beanstalk env, with a new RDS DB. Preferably, you already have a snapshot available
2. Make sure you update the RDS URL and credentials in the application-prod.yml
3. Add load balancer to EBS instance (careful with the number of instances, you may pay more for those),
   then add HTTPS listener on port 443 and assign a certificate
4. Create a Route53 A-IPV4 alias record set, with api.varfdeforma.ro pointing to the Load Balancer


# Connecting to the live DB with SequelPro

RDS DB needs to be publicly accessible and have your IP in the inbound security group.
Either that or you configure a SSL connection... which I was too lazy to do. :P

# Problems & Solutions

Q: Encoding doesn't work when saving events with special characters, altough DB collation is properly set
A: Add this to the DB URL: ?characterEncoding=UTF-8

Q: Gradle active profile is not PROD
A: On Mac, do SPRING_ACTIVE_PROFILES=prod in the terminal. Since Gradle 5, the programatic way doesn't work.

Q: In the logs you get connect() failed (111: Connection refused) while connecting to upstream,
   or "500 Bad Gateway" when you call an API
A: Set SERVER_PORT = 5000 (or whatever port you have in prod yml file)

Q: EBS Env health is critical
A: Check the DB url matches the actual DB identifier (you may have changed it)

Q: HTTPS link doesn't load
A: Point the Route53 alias record to the load balancer, not the EBS link