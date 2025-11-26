// type은 자료형에 대한 별칭을 만드는 키워드
type fn = (prevCount:number) => number // number형 전달인자 prevCount를 받아서 number형 값을 반환하는 함수 타입 

export default function Decrease(props:{ setCount: (arg0:fn) => void }) {

  return (
    <>
      <button onClick={ () => {
        props.setCount( (prevCount) => prevCount - 1 )
      }}>감소</button>
      <hr />
    </>    
  )
}