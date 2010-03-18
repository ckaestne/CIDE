package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MethodDeclaration extends GenASTNode {
  public MethodDeclaration(TypeParameters typeParameters, ResultType resultType, MethodDeclarator methodDeclarator, NameList nameList, MethodDeclarationBody methodDeclarationBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<TypeParameters>("typeParameters", typeParameters),
      new PropertyOne<ResultType>("resultType", resultType),
      new PropertyOne<MethodDeclarator>("methodDeclarator", methodDeclarator),
      new PropertyZeroOrOne<NameList>("nameList", nameList),
      new PropertyOne<MethodDeclarationBody>("methodDeclarationBody", methodDeclarationBody)
    }, firstToken, lastToken);
  }
  public MethodDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MethodDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public TypeParameters getTypeParameters() {
    return ((PropertyZeroOrOne<TypeParameters>)getProperty("typeParameters")).getValue();
  }
  public ResultType getResultType() {
    return ((PropertyOne<ResultType>)getProperty("resultType")).getValue();
  }
  public MethodDeclarator getMethodDeclarator() {
    return ((PropertyOne<MethodDeclarator>)getProperty("methodDeclarator")).getValue();
  }
  public NameList getNameList() {
    return ((PropertyZeroOrOne<NameList>)getProperty("nameList")).getValue();
  }
  public MethodDeclarationBody getMethodDeclarationBody() {
    return ((PropertyOne<MethodDeclarationBody>)getProperty("methodDeclarationBody")).getValue();
  }
}
