package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodDeclarationBody2 extends MethodDeclarationBody {
  public MethodDeclarationBody2(Token firstToken, Token lastToken) {
    super(new Property[] {
    }, firstToken, lastToken);
  }
  public MethodDeclarationBody2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodDeclarationBody2(cloneProperties(),firstToken,lastToken);
  }
}
