var drawCanvas = (function () {
    return {
        init: function (objects) {
            if (!document.getElementById('canvas')) {
                return false;
            }

            canvas = new fabric.Canvas('canvas');

            var inObjects = objects.inObjects;

            //var inObjects = [
            //    { type: "filter_casset", width: 200, height: 40, inFrame: false },
            //    { type: "clapan", width: 60, height: 40, inFrame: true },
            //    { type: "vent", width: 400, height: 40, inFrame: true },
            //    { type: "water_heater", width: 350, height: 40, inFrame: true },
            //    { type: "water_heater", width: 350, height: 40, inFrame: false },
            //    { type: "clapan", width: 60, height: 40, inFrame: false }
            //];

            var outObjects = objects.outObjects;
            //var outObjects = [
            //    { type: "filter_pocket", width: 150, height: 40, inFrame: false },
            //    { type: "vent", width: 400, height: 40, inFrame: true },
            //    { type: "water_cooler", width: 300, height: 40, inFrame: true }
            //];
            //console.log(inObjects);
            //console.log(outObjects);
            var outFrameLeftPosition = 0;
            var outFrameRightPosition = 0;

            var canvasTopPoint = 50;
            var canvasTopPadding = 10;
            var inOutBlockHeight = 200;
            var initialLeftPosition = 120;
            var lineSpacing = 40;

            var frameWidth = 15;
            var frameHeight = 15;

            var hasFrame = false;

            var componentId = 1;

            var outResult = drawImages(outObjects, initialLeftPosition, canvasTopPoint + canvasTopPadding);
            outObjects = outResult.objects;
            var totalOutWidth = outResult.totalWidth;
            var titleOutWidth = outResult.titleWidth;
            var titleOutHeight = outResult.titleHeight;

            var inResult = drawImages(inObjects, initialLeftPosition, inOutBlockHeight + canvasTopPoint + 2 * canvasTopPadding);
            inObjects = inResult.objects;
            var totalInWidth = inResult.totalWidth;
            var titleInWidth = inResult.titleWidth;
            var titleInHeight = inResult.titleHeight;

            function drawImages(objects, leftPosition, topPosition) {
                var totalWidth = 0;
                var titleWidth = 0;
                var titleHeight = 0;
                for (var i = 0; i < objects.length; i++) {

                    var frameWidthOffset = 0;

                    if (i > 0 && (!objects[i - 1].inFrame && objects[i].inFrame || objects[i - 1].inFrame && !objects[i].inFrame)) {
                        frameWidthOffset = frameWidth;
                    }

                    var imgInstance = new fabric.Image(document.getElementById(objects[i].type), {
                        left: leftPosition + frameWidthOffset,
                        top: topPosition
                    });
                    imgInstance.scaleToHeight(inOutBlockHeight);
                    leftPosition += imgInstance.getWidth() + frameWidthOffset;
                    totalWidth += imgInstance.getWidth();
                    titleWidth += objects[i].width;
                    titleHeight = Math.max(titleHeight, objects[i].height);

                    componentId++;
                    canvas.add(imgInstance);
                    objects[i].image = imgInstance;

                    if (objects[i].inFrame) {
                        hasFrame = true;
                    }
                }

                return { objects: objects, totalWidth: totalWidth, titleWidth: titleWidth, titleHeight: titleHeight };
            }


            //for (var i = 0; i < inObjects.length; i++) {
            //    //var frameWidthOffset = 0;
            //    //var frameHeightOffset = inObjects[i].inFrame ? frameWidth : 0;

            //    //if (inObjects[i].inFrame && (!inObjects[i + 1] || !inObjects[i + 1].inFrame))
            //    //    frameWidthOffset = frameWidth;

            //    //if (inObjects[i].inFrame && (!inObjects[i - 1] || !inObjects[i - 1].inFrame))
            //    //     frameWidthOffset = frameWidth;

            //    var frameWidthOffset = 0;

            //    if (i > 0 && (!outObjects[i - 1].inFrame && outObjects[i].inFrame || outObjects[i - 1].inFrame && !outObjects[i].inFrame)) {
            //        frameWidthOffset = frameWidth;
            //    }

            //    var imgInstance = new fabric.Image(document.getElementById(inObjects[i].type), {
            //        left: leftPosition,
            //        top: inOutBlockHeight + canvasTopPoint + canvasTopPadding,// + frameHeightOffset,
            //        flipX: true
            //    });
            //    imgInstance.scaleToHeight(inOutBlockHeight);
            //    leftPosition += imgInstance.getWidth();
            //    totalInWidth += imgInstance.getWidth();

            //    titleInWidth += inObjects[i].width;
            //    titleInHeight = Math.max(titleInHeight, inObjects[i].height);

            //    //if (inObjects[i].inFrame && (!inObjects[i + 1] || !inObjects[i + 1].inFrame)) {
            //        leftPosition += frameWidthOffset;
            //        //totalInWidth += frameWidthOffset;
            //   // }

            //    componentId++;

            //    inObjects[i].image = imgInstance;
            //    canvas.add(imgInstance);

            //    if (inObjects[i].inFrame) {
            //        hasFrame = true;
            //    }
            //}

            if (inObjects.length > 0) {
                drawInputDimensions();
            }

            if (outObjects.length > 0) {
                drawOutputDimensions();
            }
            drawGeneralDimensions();

            var objects = outObjects.concat(inObjects);
            for (var i = 0; i < objects.length; i++) {
                drawSequence(objects[i].image.aCoords.tr.x, objects[i].image.aCoords.tr.y, objects[i].sequence);
            }

            var canvas = document.getElementById("canvas");
            canvas = trim(canvas);
            $("canvas").hide();
            $(".canvas-container").width("100%").height("auto").css('text-align', 'center').append(canvas);
            var img = canvas.toDataURL("image/png");

            //console.log(img);
            return img;

            function drawSequence(x, y, text) {
                var circleWidth = 20;

                var circleObj = new fabric.Circle({
                    radius: circleWidth / 2,
                    fill: '#fff',
                    stroke: '#666',
                    scaleY: 1.2,
                    originX: 'center',
                    originY: 'center',
                    borderColor: 'black',
                    cornerColor: 'black'
                });

                var textObj = new fabric.Text(text.toString(), {
                    fontSize: 14,
                    fill: '#000',
                    originX: 'center',
                    originY: 'center'
                });

                var group = new fabric.Group([circleObj, textObj], {
                    left: x - circleWidth,
                    top: y + circleWidth / 2,
                });

                canvas.add(group);

            }
            function addDimensionsLine(startPoint, length, text, isVertical) {
                var triangleSize = 7;
                var angle = isVertical ? 180 : 0;
                canvas.add(new fabric.Line([0, 0, isVertical ? 0 : length, isVertical ? length : 0], { left: startPoint.left, top: startPoint.top, stroke: 'grey' }));

                var triangleLeftAngle = isVertical ? 0 : 270;
                var triangleRightAngle = isVertical ? 180 : 90;

                if (!isVertical) {
                    canvas.add(new fabric.Triangle({ width: triangleSize, height: triangleSize, fill: 'grey', left: startPoint.left - triangleSize / 2 + 2, top: startPoint.top + triangleSize / 1.7, angle: triangleLeftAngle }));
                    canvas.add(new fabric.Triangle({ width: triangleSize, height: triangleSize, fill: 'grey', left: length + startPoint.left + triangleSize / 2, top: startPoint.top - triangleSize / 2, angle: triangleRightAngle }));
                }
                else {
                    canvas.add(new fabric.Triangle({ width: triangleSize, height: triangleSize, fill: 'grey', left: startPoint.left - triangleSize / 2, top: startPoint.top - triangleSize / 1.7 + 3, angle: triangleLeftAngle }));
                    canvas.add(new fabric.Triangle({ width: triangleSize, height: triangleSize, fill: 'grey', left: startPoint.left + triangleSize / 1.6, top: length + startPoint.top + triangleSize / 2, angle: triangleRightAngle }));
                }

                if (!isVertical) {
                    var fabricText = new fabric.Text(text, { left: startPoint.left + length / 2, top: startPoint.top - 12, fill: 'grey', fontSize: 12, angle: 0 });
                    fabricText.setLeft(fabricText.getLeft() - fabricText.getWidth() / 2);
                    canvas.add(fabricText);
                }
                else {
                    var fabricText = new fabric.Text(text, { left: startPoint.left - 12, top: startPoint.top + length / 2, fill: 'grey', fontSize: 12, angle: 270 });
                    canvas.add(fabricText);
                }
            }

            function drawOutputDimensions() {
                var leftPosition = outObjects[0].image.aCoords.tl.x;
                var leftBorderDrawed = false;
                var leftBorderPosition = outObjects[0].image.aCoords.tl.x;
                var rightBorderPosition = outObjects[0].image.aCoords.tl.x;

                for (var i = 0; i < outObjects.length; i++) {
                    var imgCoordinates = outObjects[i].image.aCoords;

                    var imgInstance = outObjects[i].image;
                    var frameWidthOffset = 0;

                    if (i > 0 && (!outObjects[i - 1].inFrame && outObjects[i].inFrame || outObjects[i - 1].inFrame && !outObjects[i].inFrame)) {
                        frameWidthOffset = frameWidth;
                    }
                    var imageWidth = imgInstance.getWidth();
                    var leftPositionStart = leftPosition + frameWidthOffset;

                    canvas.add(new fabric.Line([0, 0, 0, lineSpacing], { left: leftPositionStart, top: imgCoordinates.tl.y - lineSpacing, stroke: 'grey' }));

                    if (outObjects[i].inFrame) {
                        var frameWidthRight = frameWidthOffset;
                        if (leftBorderDrawed && outObjects[i].inFrame && (!outObjects[i + 1] || !outObjects[i + 1].inFrame)) {
                            frameWidthRight = frameWidth;
                        }
                        canvas.add(new fabric.Line([0, 0, imgInstance.getWidth() + (frameWidthRight / 2), 0], { left: leftPosition + (frameWidthOffset / 2), top: imgCoordinates.tl.y - frameWidth, stroke: 'red', strokeWidth: 2 }));
                        if (!leftBorderDrawed) {
                            canvas.add(new fabric.Line([0, 0, 0, inOutBlockHeight + frameWidth], { left: leftPosition + (frameWidth / 2), top: imgCoordinates.tl.y - frameWidth, stroke: 'red', strokeWidth: 2 }));
                            leftBorderDrawed = true;
                            leftBorderPosition = leftPosition + (frameWidth / 2);
                            //frameLeft1XPos = leftPosition;

                            if (outFrameLeftPosition > 0) {
                                var compoundWidth = outFrameLeftPosition - leftPosition - (frameWidth / 2);
                                var compoundLeft = leftPosition + (frameWidth / 2);

                                if (compoundWidth < 0) {
                                    compoundWidth = -compoundWidth;
                                    compoundLeft = outFrameLeftPosition;
                                }

                                canvas.add(new fabric.Line([0, 0, compoundWidth + 2, 0], { left: compoundLeft, top: imgCoordinates.tl.y + inOutBlockHeight, stroke: 'red', strokeWidth: 2 }));
                            }
                        }
                    }

                    addDimensionsLine({ left: leftPositionStart, top: canvasTopPoint - lineSpacing + canvasTopPadding }, imgInstance.getWidth(), outObjects[i].width.toString(), false);

                    leftPosition += imgInstance.getWidth() + frameWidthOffset;
                    //totalOutWidth += imgInstance.getWidth();
                    //frameLeft2XPos = leftPosition + 2 * frameWidth;

                    //if (outObjects[i + 1] == undefined ||
                    if (leftBorderDrawed && outObjects[i].inFrame && (!outObjects[i + 1] || !outObjects[i + 1].inFrame)) {
                        canvas.add(new fabric.Line([0, 0, 0, inOutBlockHeight + frameWidth], { left: leftPosition + (frameWidth / 2), top: imgCoordinates.tl.y - frameWidth, stroke: 'red', strokeWidth: 2 }));
                        rightBorderPosition = leftPosition + (frameWidth / 2);
                        if (outFrameRightPosition > 0) {
                            var compoundWidth = outFrameRightPosition - leftPosition - (frameWidth / 2);
                            var compoundLeft = leftPosition + (frameWidth / 2);

                            if (compoundWidth < 0) {
                                compoundWidth = -compoundWidth;
                                compoundLeft = outFrameRightPosition;
                            }

                            canvas.add(new fabric.Line([0, 0, compoundWidth + 2, 0], { left: compoundLeft, top: imgCoordinates.tl.y + inOutBlockHeight, stroke: 'red', strokeWidth: 2 }));
                        }

                    }

                    canvas.add(new fabric.Line([0, 0, 0, lineSpacing], { left: leftPosition, top: imgCoordinates.tl.y - lineSpacing, stroke: 'grey' }));
                }

                if (hasFrame && !inObjects.length) {
                    canvas.add(new fabric.Line([0, 0, rightBorderPosition - leftBorderPosition + 2, 0], { left: leftBorderPosition, top: imgCoordinates.tl.y + inOutBlockHeight, stroke: 'red', strokeWidth: 2 }));
                }
            }

            function drawInputDimensions() {
                var leftPosition = inObjects[0].image.aCoords.tl.x;
                var leftBorderDrawed = false;

                for (var i = 0; i < inObjects.length; i++) {
                    var imgCoordinates = inObjects[i].image.aCoords;
                    var imgInstance = inObjects[i].image;

                    if (inObjects[i].inFrame) {
                        canvas.add(new fabric.Line([0, 0, imgInstance.getWidth() + frameWidth, 0], { left: leftPosition + (frameWidth / 2), top: imgCoordinates.bl.y + frameWidth, stroke: 'red', strokeWidth: 2 }));

                        if (!leftBorderDrawed) {
                            canvas.add(new fabric.Line([0, 0, 0, inOutBlockHeight + 1.5 * frameWidth], { left: leftPosition + (frameWidth / 2), top: imgCoordinates.tl.y - (frameWidth / 2), stroke: 'red', strokeWidth: 2 }));
                            //canvas.add(new fabric.Line([0, 0, frameLeft1XPos - leftPosition + frameWidth, 0], { left: leftPosition - frameWidth, top: imgCoordinates.tl.y - frameWidth, stroke: 'red', strokeWidth: 2 }));
                            leftBorderDrawed = true;
                            outFrameLeftPosition = leftPosition + (frameWidth / 2);
                        }
                    }

                    addDimensionsLine({ left: imgCoordinates.bl.x, top: imgCoordinates.bl.y + lineSpacing }, imgInstance.getWidth(), inObjects[i].width.toString(), false);

                    leftPosition += imgInstance.getWidth();

                    if (leftBorderDrawed && inObjects[i].inFrame && (!inObjects[i + 1] || !inObjects[i + 1].inFrame)) {
                        canvas.add(new fabric.Line([0, 0, 0, inOutBlockHeight + 1.5 * frameWidth], { left: leftPosition + 1.5 * frameWidth, top: imgCoordinates.tl.y - (frameWidth / 2), stroke: 'red', strokeWidth: 2 }));
                        outFrameRightPosition = leftPosition + 1.5 * frameWidth;
                        //canvas.add(new fabric.Line([0, 0, frameLeft2XPos - leftPosition - frameWidth, 0], { left: frameLeft2XPos, top: imgCoordinates.tl.y - frameWidth, stroke: 'red', strokeWidth: 2 }));
                    }

                    canvas.add(new fabric.Line([0, 0, 0, lineSpacing], { left: imgCoordinates.bl.x, top: imgCoordinates.bl.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, 0, lineSpacing], { left: imgCoordinates.br.x, top: imgCoordinates.br.y, stroke: 'grey' }));
                }

                if (hasFrame && !outObjects.length) {
                    canvas.add(new fabric.Line([0, 0, outFrameRightPosition - outFrameLeftPosition + 2, 0], { left: outFrameLeftPosition, top: imgCoordinates.tl.y - (frameWidth / 2), stroke: 'red', strokeWidth: 2 }));
                }
            }

            function drawGeneralDimensions() {
                if (outObjects.length > 0) {
                    var coordinates = outputCoordinates = outObjects[0].image.aCoords;
                    var lastOutputCoordinates = outObjects[outObjects.length - 1].image.aCoords;
                } else {
                    var coordinates = outputCoordinates = inObjects[0].image.aCoords;
                }

                if (inObjects.length > 0) {
                    var coordinates = inputCoordinates = inObjects[0].image.aCoords;
                    var lastInputCoordinates = inObjects[inObjects.length - 1].image.aCoords;
                } else {
                    var coordinates = inputCoordinates = outObjects[0].image.aCoords;
                }
                
                if (totalInWidth > totalOutWidth) {
                    //draw total width
                    canvas.add(new fabric.Line([0, 0, 0, 2 * lineSpacing], { left: coordinates.tl.x, top: coordinates.bl.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, 0, 2 * lineSpacing], { left: totalInWidth + initialLeftPosition + frameWidth, top: coordinates.bl.y, stroke: 'grey' }));
                    addDimensionsLine({ left: coordinates.tl.x, top: coordinates.bl.y + 2 * lineSpacing }, totalInWidth + initialLeftPosition + frameWidth - coordinates.tl.x, titleInWidth.toString(), false);

                    //if frame
                    if (hasFrame) {
                        canvas.add(new fabric.Line([0, 0, 0, 3 * lineSpacing], { left: coordinates.tl.x - frameWidth, top: coordinates.bl.y, stroke: 'grey' }));
                        canvas.add(new fabric.Line([0, 0, 0, 3 * lineSpacing], { left: totalInWidth + initialLeftPosition + 2 * frameWidth, top: coordinates.bl.y, stroke: 'grey' }));
                        addDimensionsLine({ left: coordinates.tl.x - frameWidth, top: coordinates.bl.y + 3 * lineSpacing }, totalInWidth + initialLeftPosition + 3 * frameWidth - coordinates.tl.x, (titleInWidth + 2 * frameWidth).toString(), false);
                    }
                } else {
                    //draw total width
                    canvas.add(new fabric.Line([0, 0, 0, 2 * lineSpacing], { left: coordinates.tl.x, top: coordinates.bl.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, 0, 2 * lineSpacing], { left: totalOutWidth + initialLeftPosition + frameWidth, top: coordinates.bl.y, stroke: 'grey' }));
                    addDimensionsLine({ left: coordinates.tl.x, top: coordinates.bl.y + 2 * lineSpacing }, totalOutWidth + initialLeftPosition + frameWidth - coordinates.tl.x, titleOutWidth.toString(), false);

                    //if frame
                    if (hasFrame) {
                        canvas.add(new fabric.Line([0, 0, 0, 3 * lineSpacing], { left: coordinates.tl.x - frameWidth, top: coordinates.bl.y, stroke: 'grey' }));
                        canvas.add(new fabric.Line([0, 0, 0, 3 * lineSpacing], { left: totalOutWidth + initialLeftPosition + 2 * frameWidth, top: coordinates.bl.y, stroke: 'grey' }));
                        addDimensionsLine({ left: coordinates.tl.x - frameWidth, top: coordinates.bl.y + 3 * lineSpacing }, totalOutWidth + initialLeftPosition + 3 * frameWidth - coordinates.tl.x, (titleOutWidth + 2 * frameWidth).toString(), false);
                    }
                }

                if (outObjects.length > 0) {
                    //draw right lines
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: lastOutputCoordinates.tr.x, top: lastOutputCoordinates.tr.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: lastOutputCoordinates.br.x, top: lastOutputCoordinates.br.y, stroke: 'grey' }));
                    addDimensionsLine({ left: lastOutputCoordinates.tr.x + lineSpacing, top: lastOutputCoordinates.tr.y }, inOutBlockHeight, titleOutHeight.toString(), true);
                }

                if (inObjects.length > 0) {
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: lastInputCoordinates.tr.x, top: lastInputCoordinates.tr.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: lastInputCoordinates.br.x, top: lastInputCoordinates.br.y, stroke: 'grey' }));
                    addDimensionsLine({ left: lastInputCoordinates.tr.x + lineSpacing, top: lastInputCoordinates.tr.y }, inOutBlockHeight, titleInHeight.toString(), true);
                }

                if (outObjects.length > 0) {
                    //draw left lines
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: outputCoordinates.tl.x - lineSpacing, top: outputCoordinates.tl.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: outputCoordinates.bl.x - lineSpacing, top: outputCoordinates.bl.y, stroke: 'grey' }));
                    addDimensionsLine({ left: outputCoordinates.tl.x - lineSpacing, top: outputCoordinates.tl.y }, inOutBlockHeight, titleOutHeight.toString(), true);
                }

                if (inObjects.length > 0) {
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: inputCoordinates.tl.x - lineSpacing, top: inputCoordinates.tl.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, lineSpacing, 0], { left: inputCoordinates.bl.x - lineSpacing, top: inputCoordinates.bl.y, stroke: 'grey' }));
                    addDimensionsLine({ left: inputCoordinates.tl.x - lineSpacing, top: inputCoordinates.tl.y }, inOutBlockHeight, titleInHeight.toString(), true);
                }

                //if (outObjects.length > 0 && inObjects.length > 0) {
                    canvas.add(new fabric.Line([0, 0, 2 * lineSpacing, 0], { left: outputCoordinates.tl.x - 2 * lineSpacing, top: outputCoordinates.tl.y, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, 2 * lineSpacing, 0], { left: outputCoordinates.tl.x - 2 * lineSpacing, top: inputCoordinates.bl.y, stroke: 'grey' }));
                    addDimensionsLine({ left: outputCoordinates.tl.x - 2 * lineSpacing, top: outputCoordinates.tl.y }, inputCoordinates.bl.y - outputCoordinates.tl.y, (titleInHeight + titleOutHeight).toString(), true);
                //}

                if (hasFrame) {
                    canvas.add(new fabric.Line([0, 0, 2.5 * lineSpacing, 0], { left: outputCoordinates.tl.x - 2.5 * lineSpacing, top: outputCoordinates.tl.y - frameWidth, stroke: 'grey' }));
                    canvas.add(new fabric.Line([0, 0, 2.5 * lineSpacing, 0], { left: outputCoordinates.tl.x - 2.5 * lineSpacing, top: inputCoordinates.bl.y + frameWidth, stroke: 'grey' }));
                    addDimensionsLine({ left: outputCoordinates.tl.x - 2.5 * lineSpacing, top: outputCoordinates.tl.y - frameWidth }, inputCoordinates.bl.y - outputCoordinates.tl.y + 2 * frameWidth, (titleInHeight + titleOutHeight + 2 * frameWidth).toString(), true);
                }
            }

            function trim(c) {
                var ctx = c.getContext('2d'),
                  copy = document.createElement('canvas').getContext('2d'),
                  pixels = ctx.getImageData(0, 0, c.width, c.height),
                  l = pixels.data.length,
                  i,
                  bound = {
                      top: null,
                      left: null,
                      right: null,
                      bottom: null
                  },
                  x, y;

                for (i = 0; i < l; i += 4) {
                    if (pixels.data[i + 3] !== 0) {
                        x = (i / 4) % c.width;
                        y = ~~((i / 4) / c.width);

                        if (bound.top === null) {
                            bound.top = y;
                        }

                        if (bound.left === null) {
                            bound.left = x;
                        } else if (x < bound.left) {
                            bound.left = x;
                        }

                        if (bound.right === null) {
                            bound.right = x;
                        } else if (bound.right < x) {
                            bound.right = x;
                        }

                        if (bound.bottom === null) {
                            bound.bottom = y;
                        } else if (bound.bottom < y) {
                            bound.bottom = y;
                        }
                    }
                }

                var trimHeight = bound.bottom - bound.top,
                    trimWidth = bound.right - bound.left,
                    trimmed = ctx.getImageData(bound.left, bound.top, trimWidth, trimHeight);

                copy.canvas.width = trimWidth;
                copy.canvas.height = trimHeight;
                copy.putImageData(trimmed, 0, 0);

                // open new window with trimmed image:
                return copy.canvas;
            }
        }
    }
})(drawCanvas || {})
