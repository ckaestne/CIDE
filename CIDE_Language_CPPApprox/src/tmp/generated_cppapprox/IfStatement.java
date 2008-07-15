package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfStatement extends GenASTNode {
  public IfStatement(ASTStringNode findendcb, BlockOrSingleStatement blockOrSingleStatement, ElseBlock elseBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findendcb", findendcb),
      new PropertyOne<BlockOrSingleStatement>("blockOrSingleStatement", blockOrSingleStatement),
      new PropertyZeroOrOne<ElseBlock>("elseBlock", elseBlock)
    }, firstToken, lastToken);
  }
  public IfStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfStatement(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindendcb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendcb")).getValue();
  }
  public BlockOrSingleStatement getBlockOrSingleStatement() {
    return ((PropertyOne<BlockOrSingleStatement>)getProperty("blockOrSingleStatement")).getValue();
  }
  public ElseBlock getElseBlock() {
    return ((PropertyZeroOrOne<ElseBlock>)getProperty("elseBlock")).getValue();
  }
}
