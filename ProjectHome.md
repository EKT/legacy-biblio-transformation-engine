**This project is  from now on hosted on Github: https://github.com/EKT/Biblio-Transformation-Engine. Please, refer to this source code for newer versions of BTE.**


---


The **biblio** **transformation** **engine** **(BTE)** is a java framework developed by the **Hellenic National Documentation Centre** (EKT, [www.ekt.gr](http://www.ekt.gr)) and consists of programmatic APIs for filtering and modifying records that are retrieved from various types of data sources (eg. databases, files, legacy data sources) as well as for outputing them in appropriate standards formats (eg. database files, txt, xml, Excel). The framework includes independent abstract modules that are executed seperately, offering in many cases alternative choices to the user depending of the input data set, the transformation workflow that needs to be executed and the output format that needs to be generated.

The biblio-transformation-engine is included in the core distribution of the [DSpace](http://dspace.org) digital repository platform since version 3.0 and forms the basis of the batch import feature of DSpace (i.e. importing records of various standards bibliographic formats into DSpace repositories).

![http://biblio-transformation-engine.googlecode.com/files/Biblio-Transformation-Engine_Arch.png](http://biblio-transformation-engine.googlecode.com/files/Biblio-Transformation-Engine_Arch.png)

Detailed [examples of use with detailed instructions](http://biblio-transformation-engine.googlecode.com/files/transformation%20engine%20manual_en.pdf), are available for the following transformations:

  * Import EndNote, RIS, Bibtex, MARC to DSpace

  * Transform legacy Excel or XML metadata records to CERIF

  * Generate RDF Linked Open Data from CERIF

  * Transform cultural material records to RDF Linked Open Data (compatible with CIDOC-CRM)

  * Processing metadata records of open access repositories to feed aggregators (e.g. Europeana, CLAROS, DRIVER, OpenAIRE, DART Europe, VOA3R)

  * Cleaning, normalising and modifying database or XML records

  * Filtering records from a database or input source

Licensing information: **New BSD License.**


---


The library JAR file is distributed via **Maven**  Central repository under the groupId of gr.ekt and artifactId of biblio-transformation-engine. Please check [here](http://central.maven.org/maven2/gr/ekt/biblio-transformation-engine/) for actual files.

The latest version of Biblio-Transformation-Engine can be included into Maven 2 projects using the following dependency declaration:

```
<dependency>
  <groupId>gr.ekt</groupId>
  <artifactId>biblio-transformation-engine</artifactId>
  <version>0.82</version>
</dependency>
```