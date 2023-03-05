from flask import Flask, request, render_template
import easyocr
from flask import request

app = Flask(__name__)
reader = easyocr.Reader(['en'])

def getResults(img):
    results = reader.readtext(img)
    detection=''    
    for result in results:
        detection += result[1]+'\n'
    return detection


@app.route("/", methods=["GET", "POST"])
def hello_world():
    if request.method == "GET":
        return render_template("index.html")

    if request.method == "POST":
        print("Hi")
        f = request.files["img"].read()
        test = {
            "detection": getResults(f)
        }
        return test


