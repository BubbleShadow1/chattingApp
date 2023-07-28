public class ChatActivity extends AppCompatActivity {
    private ListView chatListView;
    private EditText messageEditText;
    private Button sendButton;
    private List<Message> messages;
    private ArrayAdapter<Message> messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    chatListView = findViewById(R.id.chatListView);
     messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
   messages = new ArrayList<>();
messageAdapter = new ArrayAdapter<>(this, R.layout.message_item, messages);
chatListView.setAdapter(messageAdapter);
        sendButton.setOnClickListener(v -> {
            String messageText = messageEditText.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
                messageEditText.setText("");
            }
        });
      Message dummyMessage = new Message("Sender Name", "Hello, this is a test message!");
                
      messages.add(dummyMessage);
        messageAdapter.notifyDataSetChanged();
    }

    private void sendMessage(String messageText) {
       
        Message message = new Message("You", messageText);
        messages.add(message);
        messageAdapter.notifyDataSetChanged();
    }
}
