import { useState } from "react"
import useOutsideClick from "../../../hooks/useOnBlur"
import { ArrowDown, ArrowUp } from "feather-icons-react"

export default function DropDown({
  customClass,
  elements,
  tag1,
  tag2,
  callback,
  selected = <ArrowUp />,
  unSelected = <ArrowDown />,
}) {
  const [isOpen, setIsOpen] = useState(false)
  const ref = useOutsideClick(() => setIsOpen(false))

  let setOfElems = [
    ...new Set(
      elements?.map((elem) => {
        return tag1 ? [elem?.[tag1], elem?.[tag2]] : elem
      })
    ),
  ]

  return (
    <div ref={ref} className="z-50 relative">
      <button
        className={`${customClass || "border border-0.5 bg-red-900/30 text-white rounded hover:shadow-md hover:shadow-red-700 hover:bg-red-800 transition-all duration-200"}  `}
        onClick={(e) => {
          e.stopPropagation()
          setIsOpen((prevIsOpen) => !prevIsOpen)
        }}
      >
        {<>{isOpen ? selected : unSelected}</>}
      </button>
      {isOpen && (
        <ul className="absolute left-0 mt-1 z-50 flex bg-red-950 shadow-md rounded-md flex-col overflow-y-auto max-h-60 text-white min-w-max">
          {setOfElems.map((elem, index) => {
            return (
              <li
                key={index}
                className="px-4 py-2 hover:bg-red-800 hover:rounded-md cursor-pointer"
                onClick={() => {
                  callback(elem)
                  setIsOpen(false)
                }}
              >
                {elem?.[0] || elem}
              </li>
            )
          })}
        </ul>
      )}
    </div>
  )
}
