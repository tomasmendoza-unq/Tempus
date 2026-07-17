export default function AuthInput({
  name,
  type = "text",
  placeholder,
  onChange,
  required = true
}) {
  return (
    <input
      name={name}
      type={type}
      placeholder={placeholder}
      onChange={onChange}
      required={required}
      className="w-full border p-2 rounded focus:ring-2 focus:ring-red-900 outline-none"
    />
  );
}