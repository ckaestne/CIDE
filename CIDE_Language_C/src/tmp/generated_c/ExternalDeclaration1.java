package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExternalDeclaration1 extends ExternalDeclaration {
  public ExternalDeclaration1(FunctionDefinition functionDefinition, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FunctionDefinition>("functionDefinition", functionDefinition)
    }, firstToken, lastToken);
  }
  public ExternalDeclaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExternalDeclaration1(cloneProperties(),firstToken,lastToken);
  }
  public FunctionDefinition getFunctionDefinition() {
    return ((PropertyOne<FunctionDefinition>)getProperty("functionDefinition")).getValue();
  }
}
