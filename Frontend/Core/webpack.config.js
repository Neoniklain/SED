var path = require('path');
var webpack = require('webpack')
var HtmlWebpackPlugin = require('html-webpack-plugin');

var config = {
  entry: {
    site: [
      "./src/app/main.ts"
    ],
    polyfills: "./src/app/polyfills.ts",
    vendor: ["bootstrap"]
  },

  output: {
    path: path.resolve(__dirname, 'wwwroot/js'),
    publicPath: '/js/',
    filename: '[name].js'
  },

  resolve: {
    extensions: ['.js', '.ts']
  },

  devtool: 'source-map',

  module: {
    loaders: [
      {
        test: /\.html$/, 
        loader: 'raw-loader',
        exclude: /node_modules/
      },
      {
        test: /\.css$/, 
        loader: 'raw-loader',
        exclude: /node_modules/
      },
      { test: require.resolve("jquery"), loader: "expose-loader?$!expose-loader?jQuery" }
    ]
  },

  // common plugins for all environments
  plugins: [
    new webpack.ContextReplacementPlugin(
      // The (\\|\/) piece accounts for path separators in *nix and Windows
      /(.+)?angular(\\|\/)core(.+)?/,
      path.join(__dirname, 'src/app')
    ),
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery',
    })
  ],

  devServer: {
    https: false,
    port: 4200,
    host: "localhost",
    compress: true,
    contentBase: path.resolve(__dirname, 'wwwroot/'),
    historyApiFallback: {
      index: 'index.html'
    }
  }
};

module.exports = function(env) {
  var allowedEnvValues = ['prod', 'dev'];
  if (env == undefined) { 
    env = 'dev';
  }

  config.module.loaders = config.module.loaders.concat([
    {
        test: /\.ts$/,
        use: [
          { loader: 'awesome-typescript-loader' },
          { loader: 'angular2-template-loader' },
        ]
      }
    ]);

  if (env == 'dev') {
    config.plugins = config.plugins.concat([
      new webpack.DefinePlugin({
       'process.env': { 'API_URL': JSON.stringify('http://localhost:8080/api/') }
      })
    ]);
  }

  if (env == 'prod') {
    config.plugins = config.plugins.concat([
      new webpack.DefinePlugin({
       'process.env': { 'API_URL': JSON.stringify('api/') }
      })
    ]);
  }

  if (env == 'prod' || env == 'test') {
    config.entry.site = [
      './src/app/main-aot.ts'
    ];

    config.plugins = config.plugins.concat([
      new webpack.optimize.UglifyJsPlugin({output: {comments: false}})
    ]);
  }

  if (env == 'dev') {
    config.plugins = config.plugins.concat([
      new webpack.HotModuleReplacementPlugin()
    ]);
  }

  return config;
};
