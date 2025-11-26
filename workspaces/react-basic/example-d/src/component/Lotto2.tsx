import { useState } from "react";

export default function Lotto2() {

    const [numbersInfo, setNumbersInfo] = useState({numbers: [0, 0, 0, 0, 0, 0], average: 0})

    return (
        <>
            <table>                
                <tr>
                    <td colSpan={7}>
                        <button onClick={ () => {

                            const numbers2 = []
                            for (let i = 0; i < 6; i++) {
                                const number = Math.floor(Math.random() * 45 + 1)                                
                                numbers2.push(number);
                            }
                            numbers2.sort( (n1, n2) => n1 - n2 )                            

                            let total = 0
                            numbers2.forEach( (number) => total += number )
                            const average = Math.floor( total / numbers2.length )

                            setNumbersInfo({ numbers: numbers2, average: average })

                        } }>로또 당첨 예상 번호 뽑기</button>
                    </td>
                </tr>
                <tr>
                    {
                        // 동적으로 생성되는 요소는 고유한 key 속성 값을 지정해야 합니다.
                        numbersInfo.numbers.map( (number, idx) => <td key={idx}>{number}</td> )
                    }
                    <td>{numbersInfo.average}</td>
                </tr>
            </table>
        </>
    )

}