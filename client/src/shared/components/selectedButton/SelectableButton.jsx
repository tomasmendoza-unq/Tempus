import "./SelectableButton.css"

export function SelectableButton({ label, selected, onClick }) {
	return (
		<button
			onClick={onClick}
			className={`selectable-button ${selected ? "selectable-button--selected" : ""}`}
		>
			{label}
		</button>
	)
}
