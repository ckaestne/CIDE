package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier8 extends TypeSpecifier {
  public TypeSpecifier8(ASTStringNode signed, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("signed", signed)
    }, firstToken, lastToken);
  }
  public TypeSpecifier8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier8(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSigned() {
    return ((PropertyOne<ASTStringNode>)getProperty("signed")).getValue();
  }
}
