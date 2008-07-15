package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier2 extends TypeSpecifier {
  public TypeSpecifier2(ASTStringNode char_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("char_kw", char_kw)
    }, firstToken, lastToken);
  }
  public TypeSpecifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getChar_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("char_kw")).getValue();
  }
}
