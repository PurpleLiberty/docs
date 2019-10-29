./bin/asciidoc-coalescer.rb  -a allow-uri-read master-remote.adoc > master.adoc
sed -i 's/:leveloffset!:/:leveloffset: -1/g' master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read guide-cloud-openshift/README.adoc > guide-cloud-openshift/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read guide-getting-started/README.adoc > guide-getting-started/master.adoc
