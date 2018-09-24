const gulp = require('gulp');
const gutil = require("gulp-util");
const del = require("del");
const sourcemaps = require('gulp-sourcemaps');
const concat = require('gulp-concat');
const tsc = require('gulp-typescript');
const tsProject = tsc.createProject("tsconfig.json");
const tslint = require('gulp-tslint');
const libs = './wwwroot/lib/';
const vendor = './wwwroot/vendor/';
const backend = '../../Backend/Core/';
const shell = require('gulp-shell');
const bom = require('gulp-bom');
var exec = require('child_process').exec;
var replace = require('gulp-replace');
var rename = require('gulp-rename');

/**
 * Remove build directory.
 */
gulp.task('clean', (cb) => {
    return del(["./wwwroot/js/", "./src/**/*.js*"], cb);
});

/**
 * Lint all custom TypeScript files.
 */
gulp.task('tslint', () => {
    return gulp.src("./src/**/*.ts")
        .pipe(tslint({
            formatter: 'prose'
        }))
        .pipe(tslint.report());
});

gulp.task("build:dev", shell.task('npm run build:dev'));
gulp.task("watch:dev", shell.task('npm run watch:dev'));

gulp.task("build:prod", function() {
  var child = exec("npm run build:prod");

  child.stdout.on('data', function(data) {
    console.log(data);
  });
  child.stderr.on('data', function(data) {
    console.log(data);
  });
  child.on('close', function(code) {

  	gulp.start('build:backend');

  });
});

gulp.task("build:backend", function() {

  gulp.src(backend+'src/main/resources/application.prodaction.yml')
    	.pipe(rename({ basename: 'application'}))
	    .pipe(gulp.dest(backend+'src/main/resources'));

  gulp.src("./wwwroot/js/polyfills.js").pipe(bom())
      .pipe(gulp.dest(backend+'src/main/resources/public/js/'));

  gulp.src("./wwwroot/js/site.js").pipe(bom())
      .pipe(gulp.dest(backend+'src/main/resources/public/js/'));

  gulp.src("./wwwroot/js/vendor.js").pipe(bom())
      .pipe(gulp.dest(backend+'src/main/resources/public/js/'));

  gulp.src('./wwwroot/index.html')
    .pipe(replace('<base href="/">', '<base href="unesco">'))
    .pipe(gulp.dest(backend+'src/main/resources/public/'));
    
  gulp.src("./wwwroot/css/**/*")
      .pipe(gulp.dest(backend+'src/main/resources/public/css'));
  gulp.src("./wwwroot/fonts/**/*")
      .pipe(gulp.dest(backend+'src/main/resources/public/fonts'));
  gulp.src("./wwwroot/images/**/*")
      .pipe(gulp.dest(backend+'src/main/resources/public/images'));
  gulp.src("./wwwroot/assets/**/*")
      .pipe(gulp.dest(backend+'src/main/resources/public/assets'));
  gulp.src("./wwwroot/vendor/**/*")
      .pipe(gulp.dest(backend+'src/main/resources/public/vendor'));
  gulp.src("./wwwroot/modules/**/*")
      .pipe(gulp.dest(backend+'src/main/resources/public/modules'));

  var child = exec("buildprod");

  child.stdout.on('data', function(data) {
    console.log(data);
  });
  child.stderr.on('data', function(data) {
    console.log(data);
  });
  child.on('close', function(code) {

 	gulp.src(backend+'src/main/resources/application.development.yml')
    	.pipe(rename({ basename: 'application'}))
	    .pipe(gulp.dest(backend+'src/main/resources'));
	    
    gulp.src(backend+'target/unesco-1.5.8.RELEASE.war')
    	.pipe(rename({ basename: 'unesco'}))
	    .pipe(gulp.dest('../../LastBuild'));

  });
});