CLASSPATH=.:./src:./lib/jdom2-2.0.6.jar:./lib/sax-2.0.1.jar

bin/*.class: src/*.java
	javac --class-path $(CLASSPATH) -d bin/ $^

.PHONY: clean

clean:
	rm -f bin/*.class