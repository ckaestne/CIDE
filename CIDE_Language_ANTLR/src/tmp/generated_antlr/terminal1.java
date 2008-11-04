package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class terminal1 extends terminal {
  public terminal1(ASTStringNode char_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("char_literal", char_literal)
    }, firstToken, lastToken);
  }
  public terminal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new terminal1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getChar_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("char_literal")).getValue();
  }
}
