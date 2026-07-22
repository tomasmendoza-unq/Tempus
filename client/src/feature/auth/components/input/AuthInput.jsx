import "./AuthInput.css"

export default function AuthInput({
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
			className="auth-input"
		/>
	)
}
