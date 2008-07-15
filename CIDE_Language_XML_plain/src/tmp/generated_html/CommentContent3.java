package tmp.generated_html;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CommentContent3 extends CommentContent {
  public CommentContent3(ASTStringNode comment_word, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("comment_word", comment_word)
    }, firstToken, lastToken);
  }
  public CommentContent3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CommentContent3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getComment_word() {
    return ((PropertyOne<ASTStringNode>)getProperty("comment_word")).getValue();
  }
}
