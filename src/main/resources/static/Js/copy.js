function copyPaste(elementId){
    const element = document.getElementById(elementId);

    const textArea = document.createElement('textArea');
    textArea.value = element.textContent;
    document.body.appendChild(textArea);
    textArea.select();

    try{
        document.execCommand('copy');
        alert('Text copied to clipboard!');
    }
    catch (error){
        console.error('Failed to copy text: ',error);
    }

    document.body.removeChild(textArea);
}