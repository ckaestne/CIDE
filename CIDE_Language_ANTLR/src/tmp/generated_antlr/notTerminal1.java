package tmp.generated_antlr;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class notTerminal1 extends notTerminal {
  public notTerminal1(ASTStringNode char_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("char_literal", char_literal)
    }, firstToken, lastToken);
  }
  public notTerminal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new notTerminal1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getChar_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("char_literal")).getValue();
  }
}
