package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class FunctionDeclaration extends GenASTNode {
  public FunctionDeclaration(Identifier identifier, FormalParameterList formalParameterList, FunctionBody functionBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier),
      new PropertyZeroOrOne<FormalParameterList>("formalParameterList", formalParameterList),
      new PropertyOne<FunctionBody>("functionBody", functionBody)
    }, firstToken, lastToken);
  }
  public FunctionDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new FunctionDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
  public FormalParameterList getFormalParameterList() {
    return ((PropertyZeroOrOne<FormalParameterList>)getProperty("formalParameterList")).getValue();
  }
  public FunctionBody getFunctionBody() {
    return ((PropertyOne<FunctionBody>)getProperty("functionBody")).getValue();
  }
}
