const canvas = document.querySelector('#clock-canvas')
const ctx = canvas.getContext("2d")
const width = canvas.width
const height = canvas.height
const borderWidth = 3
const borderRadius = width / 2 - 2
const radius = borderRadius - borderWidth

function tick() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    // draw borders
    ctx.beginPath()
    ctx.arc(width / 2, height / 2, borderRadius , 0, Math.PI * 2, true)
    ctx.stroke()
    ctx.save()
    ctx.fillStyle = "black"
    ctx.fill()
    ctx.restore()
    // draw circle
    ctx.beginPath()
    ctx.arc(width / 2, height / 2, radius, 0, Math.PI * 2, true)
    ctx.stroke()
    ctx.save()
    ctx.fillStyle = "lightsteelblue"
    ctx.fill()
    ctx.restore()

    // draw point inside circle
    ctx.beginPath()
    ctx.arc(width / 2, height / 2, radius / 25, 0, Math.PI * 2, true)
    ctx.save()
    ctx.fillStyle = "black"
    ctx.fill()
    ctx.restore()

    const now = new Date()
    const utcMilllisecondsSinceEpoch = now.getTime() - (now.getTimezoneOffset() * 60 * 1000)
    const seconds = Math.round(utcMilllisecondsSinceEpoch / 1000)
    // draw hours line
    ctx.beginPath()
    const hoursR = radius * 2 / 3
    const hours = seconds / 60 / 60
    const hoursAngle = ((hours * 30 - 90) % 360) * Math.PI / 180
    let hxD = Math.cos(hoursAngle) * hoursR
    let hyD = Math.sin(hoursAngle) * hoursR
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width / 2 + hxD, height / 2 + hyD)
    ctx.stroke()

    // draw minutes line
    ctx.beginPath()
    const minutesR = radius
    const minutes = seconds / 60
    const minutesAngle = ((minutes * 6 - 90) % 360) * Math.PI / 180
    const mxD = Math.cos(minutesAngle) * minutesR
    const myD = Math.sin(minutesAngle) * minutesR
    ctx.moveTo(width / 2, height / 2)
    ctx.lineTo(width / 2 + mxD, height / 2 + myD)
    ctx.stroke()

    // draw hours checks
    for (let i = 0; i < 360; i += 30) {
        ctx.beginPath()
        let angle = i * Math.PI / 180
        let xD = Math.cos(angle)
        let yD = Math.sin(angle)
        let r1 = radius - (radius / 5)
        let r2 = radius
        ctx.moveTo(width / 2 + xD * r1, height / 2 + yD * r1)
        ctx.lineTo(width / 2 + xD * r2, height / 2 + yD * r2)
        ctx.stroke()
    }

    // draw minutes checks
    for (let i = 0; i < 360; i += 6) {
        ctx.beginPath()
        let angle = i * Math.PI / 180
        let xD = Math.cos(angle)
        let yD = Math.sin(angle)
        let r1 = radius - (radius / 5 - radius / 10)
        let r2 = radius
        ctx.moveTo(width / 2 + xD * r1, height / 2 + yD * r1)
        ctx.lineTo(width / 2 + xD * r2, height / 2 + yD * r2)
        ctx.stroke()
    }
}

setInterval(tick, 11 * 1000)
tick()