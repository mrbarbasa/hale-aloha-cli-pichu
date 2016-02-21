**Table of Content**


# 1.0 Prerequisites #

**Apache Ant 1.8.2** and **Java 1.6** must be installed before development can commence.  Any IDE will do, but if Eclipse is the IDE of your choice, the .project and .classpath files are also provided.

# 2.0 Installation #

## 2.1 Run Ant to Install Required Libraries ##

After checkout from SVN or downloading the distribution, invoke the following command in the top-level directory:

```
% ant
```

This will download Ivy, use Ivy to download other required packages, and compile the project.

If compilation fails, contact the [project members](http://code.google.com/p/hale-aloha-cli-pichu/people/list) for help.

## 2.2 Verify Installation ##

After successful compilation, verify the project as follows:

```
% ant -f verify.build.xml
```

This command downloads the JUnit, Checkstyle, PMD, JavaDoc, and FindBugs tools using Ivy if they are not already present in the distribution.  Then it runs these tools and fails the build if these tools issue any warnings.

If verification fails, contact the [project members](http://code.google.com/p/hale-aloha-cli-pichu/people/list) for help.

# 3.0 Development Guidelines #

After successful verification of the hale-aloha-cli-pichu system, development can truly commence.  Please keep the following guidelines in mind:

  * This project was developed in Eclipse using [this format](http://ics-software-engineering.googlecode.com/svn/trunk/configfiles/eclipse.format.xml).
  * The Continuous Integration server associated with this project is [Jenkins](http://dasha.ics.hawaii.edu:9859/job/hale-aloha-cli-pichu/).
  * If you wish to refer to the JavaDocs, generate them using the following command: `ant -f javadoc.build.xml`
  * Add an issue specifying any additional features/commands you plan to develop.  Make sure to keep tabs on the status of the issue and update it whenever appropriate.
  * Develop a JUnit test case anytime during development of a new feature/command to ensure its proper functionality.
  * Ensure that `ant -f verify.build.xml` runs successfully before committing changes to the repository.  Then check the [Jenkins](http://dasha.ics.hawaii.edu:9859/job/hale-aloha-cli-pichu/) server to make sure that the build passes verify there as well.