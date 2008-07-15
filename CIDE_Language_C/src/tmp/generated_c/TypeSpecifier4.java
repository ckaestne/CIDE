package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier4 extends TypeSpecifier {
  public TypeSpecifier4(ASTStringNode int_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("int_kw", int_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInt_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("int_kw")).getValue();
  }
}
