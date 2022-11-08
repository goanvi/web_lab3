const yInput = document.querySelector("#y-input");
const xInputs = document.querySelectorAll(".x-btn");
const dottedLines = document.querySelectorAll(".dotted-line");
let yLine = document.querySelector("#y-line");
let xLine = document.querySelector("#x-line");
let graph = document.querySelector(".test");
const xButtons = document.querySelector(".x-buttons");
const labels = xButtons.querySelectorAll('label')
let rValue;
let xValue;

const limit = {
  xMax: 4,
  xMin: -4,
  yMax: 3,
  yMin: -3,
  rMax: 4,
  rMin: 1,
}
document.addEventListener("DOMContentLoaded", () => {
  xValue = +(document.querySelector(".x-value").textContent);
  labels.forEach((label)=>{if (label.textContent == xValue){
    label.classList.add("active-btn")
  }})

})
graph.addEventListener("mousemove", (e) => {
  const coord = e.offsetY - 20 * (e.offsetY / 320);
  yLine.setAttribute("stroke", "red");
  if (rValue) {
    const highLimit = limit.yMax * 100 / rValue;
    const lowLimit = -(limit.yMin * 100 / rValue);
    if (coord > 150) {
      yLine.setAttribute("y1", coord <= 150 + lowLimit ? coord : 150 + lowLimit);
      yLine.setAttribute("y2", coord <= 150 + lowLimit ? coord : 150 + lowLimit);
    } else {
      yLine.setAttribute("y1", coord >= 150 - highLimit ? coord : 150 - highLimit);
      yLine.setAttribute("y2", coord >= 150 - highLimit ? coord : 150 - highLimit);
    }
  } else {
    yLine.setAttribute("y1", coord);
    yLine.setAttribute("y2", coord);
  }

  const coordX = e.offsetX - 20 * (e.offsetX / 320);
  xLine.setAttribute("stroke", "red");
  if (rValue) {
    const highLimit = limit.xMax * 100 / rValue;
    const lowLimit = -(limit.xMin * 100 / rValue);
    if (coordX > 150) {
      xLine.setAttribute("x1", coordX <= 150 + highLimit ? coordX : 150 + highLimit);
      xLine.setAttribute("x2", coordX <= 150 + highLimit ? coordX : 150 + highLimit);
    } else {
      xLine.setAttribute("x1", coordX >= 150 - lowLimit ? coordX : 150 - lowLimit);
      xLine.setAttribute("x2", coordX >= 150 - lowLimit ? coordX : 150 - lowLimit);
    }
  } else {
    xLine.setAttribute("x1", coordX);
    xLine.setAttribute("x2", coordX);
  }
});

graph.addEventListener("mouseenter", () => {
  yLine = document.querySelector("#y-line");
  xLine = document.querySelector("#x-line");
  rValue = +(document.querySelector('.r-value').textContent);
  changeRText(rValue)
});

graph.addEventListener("click", (event) => {
  labels.forEach((label)=>{label.classList.remove("active-btn");})
  const x = +(((xLine.getAttribute("x1") - 150) / 100) * +rValue).toFixed(1);
  const y = +(-((yLine.getAttribute("y1") - 150) / 100) * +rValue).toFixed(1);
  const r = +rValue;
  const xInput= document.querySelector("#hidden-form\\:hidden-x")
  const yInput= document.querySelector("#hidden-form\\:hidden-y")
  const rInput= document.querySelector("#hidden-form\\:hidden-r")
  const button= document.querySelector("#hidden-form\\:hidden-btn")
  xInput.value = x
  yInput.value = y
  rInput.value = r
  button.click();
});

xButtons.addEventListener('click',(event)=>{
  if (event.target.tagName.toLowerCase() === 'label'){
    labels.forEach((label)=>{label.classList.remove("active-btn");})
    event.target.classList.add("active-btn");
  }
})

graph.addEventListener("mouseleave", () => {
  dottedLines.forEach((dotLine) => dotLine.classList.remove("active"));
  yLine.setAttribute("stroke", "transparent");
  xLine.setAttribute("stroke", "transparent");
  changeRText("R");
});


function changeRText(rValue) {
  const rlablesWhole = document.querySelectorAll(".graph-label.r-whole-pos");
  const rlablesHalf = document.querySelectorAll(".graph-label.r-half-pos");
  const rlablesNegWhole = document.querySelectorAll(".graph-label.r-whole-neg");
  const rlablesNegHalf = document.querySelectorAll(".graph-label.r-half-neg");
  rlablesWhole.forEach((el) => (el.textContent = +rValue ? rValue : "R"));
  rlablesHalf.forEach(
    (el) => (el.textContent = +rValue / 2 ? rValue / 2 : "R/2")
  );
  rlablesNegWhole.forEach((el) => (el.textContent = -rValue ? -rValue : "-R"));
  rlablesNegHalf.forEach(
    (el) => (el.textContent = -(rValue / 2) ? -(rValue / 2) : "-R/2")
  );
}

function setInput(x, y) {
  yInput.value = y;
  xInputs.forEach((xBtn) => {
    xBtn.parentElement.classList.remove("active-btn");
    if (+xBtn.value === x) {
      xBtn.parentElement.classList.add("active-btn");
    }
  });
}

