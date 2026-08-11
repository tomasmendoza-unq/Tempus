import "./TextField.css"

export default function TextField({
	name,
	type = "text",
	placeholder,
	onChange,
	required = true,
}) {
	return (
		<input
			name={name}
			type={type}
			placeholder={placeholder}
			onChange={onChange}
			required={required}
			className="text-input"
		/>
	)
}
