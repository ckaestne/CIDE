package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Comment extends GenASTNode {
  public Comment(ASTStringNode comment_end, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("comment_end", comment_end)
    }, firstToken, lastToken);
  }
  public Comment(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Comment(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getComment_end() {
    return ((PropertyOne<ASTStringNode>)getProperty("comment_end")).getValue();
  }
}
