import { useState } from "react"



let timerId = 0

export default function Timer() {

    const [dateTime, setDateTime] = useState(new Date())    

    return (
        <>
            <table>
                <tr>
                    <td colSpan={2}>
                        {dateTime.toString()}<br />
                        {dateTime.toLocaleDateString()}&nbsp;{dateTime.toLocaleTimeString()}
                    </td>
                </tr>
                <tr>
                    <td>
                        <button onClick={ () => {
                            if (timerId !== 0) {
                                return
                            }
                            timerId = window.setInterval( () => {
                                setDateTime(new Date()) // dateTime 상태 변수 변경 -> 화면 갱신
                            }, 1000)

                        } }>ON</button>
                    </td>
                    <td>
                        <button onClick={ () => {
                            window.clearInterval(timerId)
                            timerId = 0
                        } }>OFF</button>
                    </td>
                </tr>
            </table>
        </>
    )
}