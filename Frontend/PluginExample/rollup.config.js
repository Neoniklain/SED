import resolve from 'rollup-plugin-node-resolve';
import typescript from 'rollup-plugin-typescript2';
import { uglify } from "rollup-plugin-uglify";

export default {
    input: 'src/main.ts',
    output: {
        file: '../Frontend/wwwroot/modules/plugins-example/bundle.js',
        format: 'system'
    },
    plugins: [
        uglify(),
        resolve({
            // pass custom options to the resolve plugin
            customResolveOptions: {
                moduleDirectory: 'node_modules'
            }
        }),
        typescript({
            typescript: require('typescript')
        })
    ],
    external: [
        'plugins-core',
        '@angular/core'
    ]
}