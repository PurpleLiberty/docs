./bin/asciidoc-coalescer.rb  -a allow-uri-read releasenotes/master-remote.adoc > releasenotes/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read runtime-guide/master-remote.adoc > runtime-guide/master.adoc
sed -i 's/:leveloffset!:/:leveloffset: -1/g' runtime-guide/master.adoc
