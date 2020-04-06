# Contribution Guidelines

Some tips for writing docs for Red Hat's toolchain

- If there are remote includes in the file make sure that the master.adoc is actually named master-remote.adoc
- The Product Name and Version must be the same in all master-docinfo.xml files for all books/guides being published for a version
- Each new book/guide should have a folder with its own master.adoc (or master-remote.adoc) and master-docinfo.xml file
- Each new book/guide should have a folder with symlinks to common, assemblies, modules, and img
- All assemblies and modules go in the respective folders regardless of the guide they're included in so that they can be shared if needed
