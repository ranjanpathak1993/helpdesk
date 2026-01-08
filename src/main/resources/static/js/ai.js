let aiBox=document.createElement('div');
aiBox.innerHTML=`
<div id="ai" style="position:fixed;bottom:28px;right:28px;background:#00eaff;
padding:14px;border-radius:50%;cursor:pointer;font-weight:700;color:#003d4f">
AI
</div>
<div id="chat" style="display:none;position:fixed;bottom:90px;right:28px;
width:280px;background:#002b45;padding:15px;border-radius:10px;color:white;">
<b>🤖 Ask IT Help:</b><br>
<input id="msg" style="width:90%;padding:7px;margin:8px 0;border-radius:5px;">
<div id="reply" style="font-size:14px;"></div>
</div>`;

document.body.appendChild(aiBox);

ai.onclick=()=> chat.style.display = chat.style.display=='none'?'block':'none';

document.getElementById('msg').addEventListener('input',e=>{
 let q=e.target.value.toLowerCase();
 if(q.includes("vpn")) reply.innerHTML="Try restart adapter + reconnect VPN.";
 else if(q.includes("printer")) reply.innerHTML="Check cable + restart spooler.";
 else if(q.includes("email")) reply.innerHTML="Outlook profile reset recommended.";
 else reply.innerHTML="Type related to VPN, Printer or Email.";
});
