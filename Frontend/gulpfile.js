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
const shell = require('gulp-shell');

/**
 * Remove build directory.
 */
gulp.task('clean', (cb) => {
    return del(["./dist/", "./wwwroot/js/", "./src/**/*.js*", "!./src/app/locales/*.json"], cb);
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

gulp.task("build:test", ['build:restore'], shell.task('npm run build:test'));
gulp.task("build:prod", ['build:restore'], shell.task('npm run build:prod'));
gulp.task("build:dev", ['build:restore'], shell.task('npm run build:dev'));
gulp.task("watch:dev", ['restore'], shell.task('npm run watch:dev'));

gulp.task('restore', [
    'restore:bootstrap',
    'restore:pleasewait',
    'restore:primeng',
]);

gulp.task('build:restore', [
    'restore:primeng',
]);

gulp.task('restore:primeng', function () {
    gulp.src([
        'node_modules/primeng/**',,
    ]).pipe(gulp.dest('./src/app/aot/node_modules/primeng'));
});

gulp.task('restore:bootstrap', function () {
    gulp.src([
        'node_modules/bootstrap/dist/**/*.min.css',
        'node_modules/bootstrap/dist/**/*.eot',
        'node_modules/bootstrap/dist/**/*.svg',
        'node_modules/bootstrap/dist/**/*.ttf',
        'node_modules/bootstrap/dist/**/*.woff',
        'node_modules/bootstrap/dist/**/*.woff2',
        'node_modules/bootstrap-material-design/dist/**/*.min.css'
    ]).pipe(gulp.dest(vendor + 'bootstrap'));
});


gulp.task('restore:pleasewait', function () {
    gulp.src([
        'node_modules/please-wait/build/**/*.css',
        'node_modules/please-wait/build/**/*.min.js',
    ]).pipe(gulp.dest(vendor + 'please-wait'));
});