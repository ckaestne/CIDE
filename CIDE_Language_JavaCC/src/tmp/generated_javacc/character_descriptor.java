package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class character_descriptor extends GenASTNode {
  public character_descriptor(ASTStringNode string_literal, ASTStringNode string_literal1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("string_literal", string_literal),
      new PropertyZeroOrOne<ASTStringNode>("string_literal1", string_literal1)
    }, firstToken, lastToken);
  }
  public character_descriptor(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new character_descriptor(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getString_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("string_literal")).getValue();
  }
  public ASTStringNode getString_literal1() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("string_literal1")).getValue();
  }
}
