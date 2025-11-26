
// export default function FunctionComponent(props:{phone:string, age:number}) {

//     return (
//         <>
//             <h2>함수 컴포넌트 입니다.</h2>
//             <h3>전화번호 : { props.phone }</h3>
//             <h3>나이 : { props.age }</h3>
//         </>
//     )

// }

export default function FunctionComponent(props:{phone:string, age:number}) {

    const { phone, age } = props

    return (
        <>
            <h2>함수 컴포넌트 입니다.</h2>
            <h3>전화번호 : { phone }</h3>
            <h3>나이 : { age }</h3>
            <button onClick={ () => alert("[" + phone + "]") }>여기도 눌러 주세요</button>
        </>
    )

}

// export default function FunctionComponent({phone, age}:{phone:string, age:number}) {

//     return (
//         <>
//             <h2>함수 컴포넌트 입니다.</h2>
//             <h3>전화번호 : { phone }</h3>
//             <h3>나이 : { age }</h3>
//         </>
//     )

// }