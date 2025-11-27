import { useState } from "react"

export default function CurrencyExchange() {

    const [from, setFrom] = useState("dollar")
    const [to, setTo] = useState("won")
    const [fromValue, setFromValue] = useState("")
    const [toValue, setToValue] = useState("")
    // Record<string, number> : key는 string, value는 number인 JSON Type
    const exchangeRate: Record<string,number> = {
        "won_to_won": 1,
        "won_to_dollar": 1/1467,
        "won_to_yuan": 1/207,
        "yuan_to_won": 207,
        "yuan_to_dollar": 1/7,
        "yuan_to_yuan": 1,
        "dollar_to_won": 1467,
        "dollar_to_dollar": 1,
        "dollar_to_yuan": 7,
    }

    return(
        <>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <select value={from} 
                                    onChange={ (event) => {
                                        setFrom(event.target.value)
                                    }}>
                                <option value="won">원</option>
                                <option value="dollar">달러</option>
                                <option value="yuan">위안</option>
                            </select>
                            <br />
                            <input  type="text"
                                    value={fromValue}
                                    onChange={ (event) => {
                                        setFromValue(event.target.value)
                                    }} />
                        </td>
                        <td>
                            <button onClick={ () => {
                                if (fromValue.length == 0 || isNaN(Number(fromValue))) { // 숫자로 변환할 수 없다면
                                    alert('숫자 값을 입력하세요')
                                    return
                                }
                                const exchangeRateKey = `${from}_to_${to}`
                                setToValue((parseFloat(fromValue) * exchangeRate[exchangeRateKey]).toString())
                            }}>변환</button>
                        </td>
                        <td>
                            <select value={to}
                                    onChange={ (event) => {
                                        setTo(event.target.value)
                                    }}>
                                <option value="won">원</option>
                                <option value="dollar">달러</option>
                                <option value="yuan">위안</option>
                            </select>
                            <br />
                            <input type="text" value={toValue} readOnly />
                        </td>
                    </tr>
                </tbody>
            </table>
        </>
    )

}