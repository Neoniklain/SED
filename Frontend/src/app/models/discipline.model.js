"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var fieldOfKnowledge_model_1 = require("./fieldOfKnowledge.model");
var Discipline = (function () {
    function Discipline() {
        this.name = "";
        this.datecreate = new Date();
        this.fieldOfKnowledge = new fieldOfKnowledge_model_1.FieldOfKnowledgeModel();
    }
    return Discipline;
}());
exports.Discipline = Discipline;
