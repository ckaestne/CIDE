package tmp.generated_people;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Misc1 extends Misc {
  public Misc1(Comment comment, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Comment>("comment", comment)
    }, firstToken, lastToken);
  }
  public Misc1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Misc1(cloneProperties(),firstToken,lastToken);
  }
  public Comment getComment() {
    return ((PropertyOne<Comment>)getProperty("comment")).getValue();
  }
}
