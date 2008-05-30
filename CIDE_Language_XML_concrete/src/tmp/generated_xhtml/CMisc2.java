package tmp.generated_xhtml;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CMisc2 extends CMisc {
  public CMisc2(Comment comment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Comment>("comment", comment)
    }, firstToken, lastToken);
  }
  public CMisc2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new CMisc2(cloneProperties(),firstToken,lastToken);
  }
  public Comment getComment() {
    return ((PropertyOne<Comment>)getProperty("comment")).getValue();
  }
}
