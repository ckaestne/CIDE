package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier1 extends TypeSpecifier {
  public TypeSpecifier1(ASTStringNode void_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("void_kw", void_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVoid_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("void_kw")).getValue();
  }
}
