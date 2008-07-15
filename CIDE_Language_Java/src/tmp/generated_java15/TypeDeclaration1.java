package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDeclaration1 extends TypeDeclaration {
  public TypeDeclaration1(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public TypeDeclaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDeclaration1(cloneProperties(),firstToken,lastToken);
  }
}
