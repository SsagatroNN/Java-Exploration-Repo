## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

There are two external jars included in this project, both can be found in the lib folder. They are added to classpath during compilation adn runtime.

## XML-Parsing
There are two main types of parsers, DOM parsers and SAX parsers.

DOM parsers:
- Javas DOM Parser
- JDOM Parser
- DOM4j Parser

SAX parsers:
- SAX Parser
- StAX Parser

There are some other API's which handle XML Parsing such as JAXB and XSLT API's.

## Key Differences between SAX and DOM Parsing

DOM (Document Object Model) parsers load the entire tree object into memory, i.e the entire xml file is parsed into a tree. Unlike SAX (Simple Api for XML) parsers which are event driven only load some parts of the tree into memory. (See `src/SAXParser.java` where a handler class is used to handle xml data). SAX Parsers are faster than DOM Parsers and are alot more memory efficient since they done need to load the entire tree into memory. SAX parsers cannot represent the internal structure of the XML Object as they are read only. While DOM parsers represent the XML data as a tree object.

## XPath Expression Language

The recommended method by W3C to traverse/ Read nodes from an XML DOM object is using Xpath expressions. XPath stands for XML Path Langauge. It uses the widley uses linux file system paths to identify/find nodes. The langauge can be tested using `src/XPathParser.java` using the following syntax.

    ./run -xpath <xpath-expression>

The program will return the Text content of the node/nodes obtained. By default the program will run ```//book``` which will return a nodelist of all books in the xml tree regardless of depth.

For detailed documentation of the XPath Language: [Documentation](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/xml/xpath/package-summary.html)

### Running the Parsers
- ./run.sh -dom -> runs DomParser.java.
- ./run.sh -jdom -> runs JdomReader.java.
- ./run.sh -sax -> runs SAXParser.java.
- ./run.sh -xpath -> runs XPathParser.java with default expression (`//book`).
- ./run.sh -xpath \<Your-Expression\> -> runs XPathParser.java with your input expression.

