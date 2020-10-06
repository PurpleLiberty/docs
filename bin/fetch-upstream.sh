./bin/asciidoc-coalescer.rb  -a allow-uri-read releasenotes/master-remote.adoc > releasenotes/master.adoc
./bin/asciidoc-coalescer.rb  -a allow-uri-read runtime-guide/master-remote.adoc > runtime-guide/master.adoc
sed -i 's/:leveloffset!:/:leveloffset: -1/g' runtime-guide/master.adoc
targets=$(cat releasenotes/master.adoc | grep 'image::' | cut -c8- | awk -F[ '{ print $1 }')
for image in $targets; do
    echo "Fetching image: '$image'"
    curl https://raw.githubusercontent.com/OpenLiberty/blogs/prod/$image --create-dirs -o ../$image
done