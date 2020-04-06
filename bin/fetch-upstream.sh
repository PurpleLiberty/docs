./bin/asciidoc-coalescer.rb  -a allow-uri-read master-remote.adoc > master.adoc
sed -i 's/:leveloffset!:/:leveloffset: -1/g' master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read guide-cloud-openshift/README.adoc > guide-cloud-openshift/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read guide-getting-started/README.adoc > guide-getting-started/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read releasenotes/master-remote.adoc > releasenotes/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read demo/master-remote.adoc > demo/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read runtime-guide/master-remote.adoc > runtime-guide/master.adoc
