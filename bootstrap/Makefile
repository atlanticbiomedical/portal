BOOTSTRAP = ../css/bootstrap.css
BOOTSTRAP_LESS = ./less/bootstrap.less
BOOTSTRAP_RESPONSIVE = ../css/bootstrap-responsive.css
BOOTSTRAP_RESPONSIVE_LESS = ./less/responsive.less
DATE=$(shell date +%I:%M%p)
CHECK=\033[32m✔\033[39m
HR=\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#


#
# BUILD DOCS
#

build:
	recess --compress less/bootstrap.less > ../src/com/biomed/resources/css/bootstrap.min.css
	recess --compress less/bootstrap-responsive.less > ../src/com/biomed/resources/css/bootstrap-responsive.min.css

	mkdir -p tmp
	cat js/bootstrap-transition.js js/bootstrap-alert.js js/bootstrap-button.js js/bootstrap-carousel.js js/bootstrap-collapse.js js/bootstrap-dropdown.js js/bootstrap-modal.js js/bootstrap-tooltip.js js/bootstrap-popover.js js/bootstrap-scrollspy.js js/bootstrap-tab.js js/bootstrap-typeahead.js js/bootstrap-affix.js > tmp/bootstrap.js
	uglifyjs -nc tmp/bootstrap.js > tmp/bootstrap.min.js
	echo "/*!\n* Bootstrap.js by @fat & @mdo\n* Copyright 2012 Twitter, Inc.\n* http://www.apache.org/licenses/LICENSE-2.0.txt\n*/" > tmp/copyright.js
	cat tmp/copyright.js tmp/bootstrap.min.js > ../src/com/biomed/resources/js/bootstrap.min.js


#
# CLEANS THE ROOT DIRECTORY OF PRIOR BUILDS
#

clean:
	rm -r bootstrap

#
# BUILD SIMPLE BOOTSTRAP DIRECTORY
# recess & uglifyjs are required
#

bootstrap:
	mkdir -p bootstrap/img
	mkdir -p bootstrap/css
	mkdir -p bootstrap/js
	cp img/* bootstrap/img/
	recess --compile ${BOOTSTRAP_LESS} > bootstrap/css/bootstrap.css
	recess --compress ${BOOTSTRAP_LESS} > bootstrap/css/bootstrap.min.css
	recess --compile ${BOOTSTRAP_RESPONSIVE_LESS} > bootstrap/css/bootstrap-responsive.css
	recess --compress ${BOOTSTRAP_RESPONSIVE_LESS} > bootstrap/css/bootstrap-responsive.min.css
	uglifyjs -nc bootstrap/js/bootstrap.js > bootstrap/js/bootstrap.min.tmp.js
	rm bootstrap/js/copyright.js bootstrap/js/bootstrap.min.tmp.js

#
# MAKE FOR GH-PAGES 4 FAT & MDO ONLY (O_O  )
#

gh-pages: bootstrap docs
	rm -f docs/assets/bootstrap.zip
	zip -r docs/assets/bootstrap.zip bootstrap
	rm -r bootstrap
	rm -f ../bootstrap-gh-pages/assets/bootstrap.zip
	node docs/build production
	cp -r docs/* ../bootstrap-gh-pages

#
# WATCH LESS FILES
#

watch:
	echo "Watching less files..."; \
	watchr -e "watch('less/.*\.less') { system 'make' }"

#
# HAUNT GITHUB ISSUES 4 FAT & MDO ONLY (O_O  )
#

haunt:
	@haunt .issue-guidelines.js https://github.com/twitter/bootstrap


.PHONY: docs watch gh-pages
