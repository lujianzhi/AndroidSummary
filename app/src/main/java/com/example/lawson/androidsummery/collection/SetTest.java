package com.example.lawson.androidsummery.collection;

import com.example.lawson.androidsummery.detectmemory.entity.ObjImplComparable;
import com.example.lawson.androidsummery.detectmemory.entity.ObjWithoutContext;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	private static Set<ObjWithoutContext> hashSet;
	private static Set<ObjWithoutContext> linkedHashSet;

	private static Set<ObjImplComparable> treeSet;

	public static void main(String[] args) {

		// SET
		// HashSet
		// generateHashSet();
		// LinedHashSet
		// generateLinkedHashSet();
		// 新增一条数据
		// addObj();
		// 移除一条数据
		// removeObj();

		// TreeSet
		generateTreeSet();
	}

	private static void generateTreeSet() {
		treeSet = new TreeSet<>();
		long start = System.currentTimeMillis();
		treeSet.add(new ObjImplComparable(3, "content-1", "title-1"));
		treeSet.add(new ObjImplComparable(1, "content-2", "title-1"));
		treeSet.add(new ObjImplComparable(1, "content-3", "title-1"));
		treeSet.add(new ObjImplComparable(3, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(33, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(5, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(32, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(3, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(4, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(3, "content-4", "title-1"));
		treeSet.add(new ObjImplComparable(9, "content-4", "title-1"));
		// for (int i = 0; i < 100000; i++) {
		// treeSet.add(new ObjImplComparable("content-" + i, "title-" + i));
		// }
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println(treeSet.toString());
		System.out.println("添加数据:" + duration);
	}

	private static void addObj() {
		long start = System.currentTimeMillis();
		hashSet.add(new ObjWithoutContext("新增", "新增"));
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("添加数据:" + duration);
	}

	private static void removeObj() {
		long start = System.currentTimeMillis();
		hashSet.remove(new ObjWithoutContext("content-34523", "title-34523"));
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println("删除数据:" + duration);
	}

	private static void generateHashSet() {
		hashSet = new HashSet<>();
		long start = System.currentTimeMillis();
		hashSet.add(new ObjWithoutContext("content-1", "title-1"));
		hashSet.add(new ObjWithoutContext("content-2", "title-1"));
		hashSet.add(new ObjWithoutContext("content-3", "title-1"));
		hashSet.add(new ObjWithoutContext("content-4", "title-1"));
		for (int i = 0; i < 100000; i++) {
			hashSet.add(new ObjWithoutContext("content-" + i, "title-" + i));
		}
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println(hashSet.toString());
		System.out.println("添加数据:" + duration);
	}

	private static void generateLinkedHashSet() {
		linkedHashSet = new LinkedHashSet<>();
		long start = System.currentTimeMillis();
		linkedHashSet.add(new ObjWithoutContext("content-1", "title-1"));
		linkedHashSet.add(new ObjWithoutContext("content-2", "title-1"));
		linkedHashSet.add(new ObjWithoutContext("content-3", "title-1"));
		linkedHashSet.add(new ObjWithoutContext("content-4", "title-1"));
		for (int i = 0; i < 100000; i++) {
			linkedHashSet.add(new ObjWithoutContext("content-" + i, "title-" + i));
		}
		long end = System.currentTimeMillis();
		long duration = end - start;
		System.out.println(linkedHashSet.toString());
		System.out.println("添加数据:" + duration);
	}

}
