"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var user_model_1 = require("../account/user.model");
var News = (function () {
    function News() {
        this.header = "";
        this.author = new user_model_1.User();
        this.content = "";
        this.tags = "";
        this.image = "";
        this.date = new Date();
    }
    return News;
}());
exports.News = News;
