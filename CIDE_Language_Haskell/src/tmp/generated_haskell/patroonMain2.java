package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patroonMain2 extends patroonMain {
  public patroonMain2(ASTTextNode text6, literal literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTTextNode>("text6", text6),
      new PropertyOne<literal>("literal", literal)
    }, firstToken, lastToken);
  }
  public patroonMain2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new patroonMain2(cloneProperties(),firstToken,lastToken);
  }
  public ASTTextNode getText6() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text6")).getValue();
  }
  public literal getLiteral() {
    return ((PropertyOne<literal>)getProperty("literal")).getValue();
  }
}
