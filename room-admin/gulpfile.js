var gulp = require('gulp'), //将上面down下来的插件都要引入，以便需要
    gutil = require('gulp-util'),
    wiredep = require('wiredep').stream;


gulp.task('wiredep', function () {  // 创建task任务:可以在cmd命令中执行任务
    gulp.src('src/main/index.html')
        .pipe(wiredep({  // 调用插件wiredep执行方法
            optional: 'configuration',
            goes: 'here'
        }))
        .pipe(gulp.dest('src/main'))
})



