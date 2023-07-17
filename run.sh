#!/bin/bash
OPTION=$1
XPATHEXPR=$2
JAVA_ARGS='-ea -XX:+ShowCodeDetailsInExceptionMessages'
COMPILER_DIRS=./bin/:./lib/jdom2-2.0.6.jar:./lib/sax-2.0.1.jar


# Compile all
make

if [ "$OPTION" == "clean" ] 
then
    echo '#################################################################'
    echo '                  Cleaning all files                             '
    echo '#################################################################'
    make clean
    exit 0
fi

# Run Jdom Reader
if [ "$OPTION" == "-jdom" ] 
then
    echo '#################################################################'
    echo '                  Running JDom Reader                            '
    echo '#################################################################'
    echo java $JAVA_ARGS -cp $COMPILER_DIRS JdomReader ./xml/books.xml
    java $JAVA_ARGS -cp $COMPILER_DIRS JdomReader ./xml/books.xml
    echo ''
fi

# Run Dom Parser
if [ "$OPTION" == "-dom" ] 
then
    echo '#################################################################'
    echo '                  Running DOM Parser                             '
    echo '#################################################################'
    echo java $JAVA_ARGS -cp $COMPILER_DIRS DomParser ./xml/books.xml
    java $JAVA_ARGS -cp $COMPILER_DIRS DomParser ./xml/books.xml
    echo ''
fi

# Run SAX Parser
if [ "$OPTION" == "-sax" ] 
then
    echo '#################################################################'
    echo '                  Running SAX Parser                             '
    echo '#################################################################'
    echo java $JAVA_ARGS -cp $COMPILER_DIRS SAXParser ./xml/books.xml
    java $JAVA_ARGS -cp $COMPILER_DIRS SAXParser ./xml/books.xml
    echo ''
fi

# Run XPath Parser
if [ "$OPTION" == "-xpath" ] 
then
    
    if [ "$XPATHEXPR" == "" ]
    then
        echo 'Invalid XPath Expression'
        # exit 1
        XPATHEXPR='//book'
        echo 'Default XPath Expression: ' $XPATHEXPR
    fi

    echo '#################################################################'
    echo '                  Running XPath Parser                           '
    echo '#################################################################'
    echo java $JAVA_ARGS -cp $COMPILER_DIRS XPathParser ./xml/books.xml $XPATHEXPR
    java $JAVA_ARGS -cp $COMPILER_DIRS XPathParser ./xml/books.xml $XPATHEXPR
    echo ''
fi